import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';
import { InvoiceInfoService } from './invoice-info.service';
import { IEInvoiceInfo } from 'app/shared/model/e-invoice-info.model';
import { EInvoiceInfoService } from 'app/entities/e-invoice-info';

@Component({
    selector: 'jhi-invoice-info-update',
    templateUrl: './invoice-info-update.component.html'
})
export class InvoiceInfoUpdateComponent implements OnInit {
    private _invoiceInfo: IInvoiceInfo;
    isSaving: boolean;

    extracts: IEInvoiceInfo[];
    createTime: string;
    updateTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private invoiceInfoService: InvoiceInfoService,
        private eInvoiceInfoService: EInvoiceInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ invoiceInfo }) => {
            this.invoiceInfo = invoiceInfo;
        });
        this.eInvoiceInfoService.query({ filter: 'invoiceinfo-is-null' }).subscribe(
            (res: HttpResponse<IEInvoiceInfo[]>) => {
                if (!this.invoiceInfo.extractId) {
                    this.extracts = res.body;
                } else {
                    this.eInvoiceInfoService.find(this.invoiceInfo.extractId).subscribe(
                        (subRes: HttpResponse<IEInvoiceInfo>) => {
                            this.extracts = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.invoiceInfo.createTime = moment(this.createTime, DATE_TIME_FORMAT);
        this.invoiceInfo.updateTime = moment(this.updateTime, DATE_TIME_FORMAT);
        if (this.invoiceInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.invoiceInfoService.update(this.invoiceInfo));
        } else {
            this.subscribeToSaveResponse(this.invoiceInfoService.create(this.invoiceInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IInvoiceInfo>>) {
        result.subscribe((res: HttpResponse<IInvoiceInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEInvoiceInfoById(index: number, item: IEInvoiceInfo) {
        return item.id;
    }
    get invoiceInfo() {
        return this._invoiceInfo;
    }

    set invoiceInfo(invoiceInfo: IInvoiceInfo) {
        this._invoiceInfo = invoiceInfo;
        this.createTime = moment(invoiceInfo.createTime).format(DATE_TIME_FORMAT);
        this.updateTime = moment(invoiceInfo.updateTime).format(DATE_TIME_FORMAT);
    }
}
