import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';
import { Principal } from 'app/core';
import { InvoiceInfoService } from './invoice-info.service';

@Component({
    selector: 'jhi-invoice-info',
    templateUrl: './invoice-info.component.html'
})
export class InvoiceInfoComponent implements OnInit, OnDestroy {
    invoiceInfos: IInvoiceInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private invoiceInfoService: InvoiceInfoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.invoiceInfoService.query().subscribe(
            (res: HttpResponse<IInvoiceInfo[]>) => {
                this.invoiceInfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInInvoiceInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IInvoiceInfo) {
        return item.id;
    }

    registerChangeInInvoiceInfos() {
        this.eventSubscriber = this.eventManager.subscribe('invoiceInfoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
