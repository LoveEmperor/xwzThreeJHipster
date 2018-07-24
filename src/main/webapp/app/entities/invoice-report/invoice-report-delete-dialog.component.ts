import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvoiceReport } from 'app/shared/model/invoice-report.model';
import { InvoiceReportService } from './invoice-report.service';

@Component({
    selector: 'jhi-invoice-report-delete-dialog',
    templateUrl: './invoice-report-delete-dialog.component.html'
})
export class InvoiceReportDeleteDialogComponent {
    invoiceReport: IInvoiceReport;

    constructor(
        private invoiceReportService: InvoiceReportService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.invoiceReportService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'invoiceReportListModification',
                content: 'Deleted an invoiceReport'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-invoice-report-delete-popup',
    template: ''
})
export class InvoiceReportDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ invoiceReport }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InvoiceReportDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.invoiceReport = invoiceReport;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
