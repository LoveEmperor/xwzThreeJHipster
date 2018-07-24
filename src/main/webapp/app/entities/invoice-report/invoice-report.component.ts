import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IInvoiceReport } from 'app/shared/model/invoice-report.model';
import { Principal } from 'app/core';
import { InvoiceReportService } from './invoice-report.service';

@Component({
    selector: 'jhi-invoice-report',
    templateUrl: './invoice-report.component.html'
})
export class InvoiceReportComponent implements OnInit, OnDestroy {
    invoiceReports: IInvoiceReport[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private invoiceReportService: InvoiceReportService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.invoiceReportService.query().subscribe(
            (res: HttpResponse<IInvoiceReport[]>) => {
                this.invoiceReports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInInvoiceReports();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IInvoiceReport) {
        return item.id;
    }

    registerChangeInInvoiceReports() {
        this.eventSubscriber = this.eventManager.subscribe('invoiceReportListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
