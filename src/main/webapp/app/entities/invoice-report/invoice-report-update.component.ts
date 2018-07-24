import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IInvoiceReport } from 'app/shared/model/invoice-report.model';
import { InvoiceReportService } from './invoice-report.service';

@Component({
    selector: 'jhi-invoice-report-update',
    templateUrl: './invoice-report-update.component.html'
})
export class InvoiceReportUpdateComponent implements OnInit {
    private _invoiceReport: IInvoiceReport;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(private invoiceReportService: InvoiceReportService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ invoiceReport }) => {
            this.invoiceReport = invoiceReport;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.invoiceReport.createTime = moment(this.createTime, DATE_TIME_FORMAT);
        this.invoiceReport.updateTime = moment(this.updateTime, DATE_TIME_FORMAT);
        if (this.invoiceReport.id !== undefined) {
            this.subscribeToSaveResponse(this.invoiceReportService.update(this.invoiceReport));
        } else {
            this.subscribeToSaveResponse(this.invoiceReportService.create(this.invoiceReport));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IInvoiceReport>>) {
        result.subscribe((res: HttpResponse<IInvoiceReport>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get invoiceReport() {
        return this._invoiceReport;
    }

    set invoiceReport(invoiceReport: IInvoiceReport) {
        this._invoiceReport = invoiceReport;
        this.createTime = moment(invoiceReport.createTime).format(DATE_TIME_FORMAT);
        this.updateTime = moment(invoiceReport.updateTime).format(DATE_TIME_FORMAT);
    }
}
