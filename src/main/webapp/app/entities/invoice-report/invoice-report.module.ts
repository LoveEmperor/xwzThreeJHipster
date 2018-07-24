import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InvoiceprojectSharedModule } from 'app/shared';
import {
    InvoiceReportComponent,
    InvoiceReportDetailComponent,
    InvoiceReportUpdateComponent,
    InvoiceReportDeletePopupComponent,
    InvoiceReportDeleteDialogComponent,
    invoiceReportRoute,
    invoiceReportPopupRoute
} from './';

const ENTITY_STATES = [...invoiceReportRoute, ...invoiceReportPopupRoute];

@NgModule({
    imports: [InvoiceprojectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InvoiceReportComponent,
        InvoiceReportDetailComponent,
        InvoiceReportUpdateComponent,
        InvoiceReportDeleteDialogComponent,
        InvoiceReportDeletePopupComponent
    ],
    entryComponents: [
        InvoiceReportComponent,
        InvoiceReportUpdateComponent,
        InvoiceReportDeleteDialogComponent,
        InvoiceReportDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InvoiceprojectInvoiceReportModule {}
