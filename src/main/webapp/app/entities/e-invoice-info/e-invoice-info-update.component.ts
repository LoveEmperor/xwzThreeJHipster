import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEInvoiceInfo } from 'app/shared/model/e-invoice-info.model';
import { EInvoiceInfoService } from './e-invoice-info.service';

@Component({
    selector: 'jhi-e-invoice-info-update',
    templateUrl: './e-invoice-info-update.component.html'
})
export class EInvoiceInfoUpdateComponent implements OnInit {
    private _eInvoiceInfo: IEInvoiceInfo;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(private eInvoiceInfoService: EInvoiceInfoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ eInvoiceInfo }) => {
            this.eInvoiceInfo = eInvoiceInfo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.eInvoiceInfo.createTime = moment(this.createTime, DATE_TIME_FORMAT);
        this.eInvoiceInfo.updateTime = moment(this.updateTime, DATE_TIME_FORMAT);
        if (this.eInvoiceInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.eInvoiceInfoService.update(this.eInvoiceInfo));
        } else {
            this.subscribeToSaveResponse(this.eInvoiceInfoService.create(this.eInvoiceInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEInvoiceInfo>>) {
        result.subscribe((res: HttpResponse<IEInvoiceInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get eInvoiceInfo() {
        return this._eInvoiceInfo;
    }

    set eInvoiceInfo(eInvoiceInfo: IEInvoiceInfo) {
        this._eInvoiceInfo = eInvoiceInfo;
        this.createTime = moment(eInvoiceInfo.createTime).format(DATE_TIME_FORMAT);
        this.updateTime = moment(eInvoiceInfo.updateTime).format(DATE_TIME_FORMAT);
    }
}
