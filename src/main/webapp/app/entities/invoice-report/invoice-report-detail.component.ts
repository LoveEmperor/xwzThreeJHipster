import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvoiceReport } from 'app/shared/model/invoice-report.model';

@Component({
    selector: 'jhi-invoice-report-detail',
    templateUrl: './invoice-report-detail.component.html'
})
export class InvoiceReportDetailComponent implements OnInit {
    invoiceReport: IInvoiceReport;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ invoiceReport }) => {
            this.invoiceReport = invoiceReport;
        });
    }

    previousState() {
        window.history.back();
    }
}
