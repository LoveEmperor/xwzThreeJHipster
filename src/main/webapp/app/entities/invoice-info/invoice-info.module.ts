import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InvoiceprojectSharedModule } from 'app/shared';
import {
    InvoiceInfoComponent,
    InvoiceInfoDetailComponent,
    InvoiceInfoUpdateComponent,
    InvoiceInfoDeletePopupComponent,
    InvoiceInfoDeleteDialogComponent,
    invoiceInfoRoute,
    invoiceInfoPopupRoute
} from './';

const ENTITY_STATES = [...invoiceInfoRoute, ...invoiceInfoPopupRoute];

@NgModule({
    imports: [InvoiceprojectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InvoiceInfoComponent,
        InvoiceInfoDetailComponent,
        InvoiceInfoUpdateComponent,
        InvoiceInfoDeleteDialogComponent,
        InvoiceInfoDeletePopupComponent
    ],
    entryComponents: [InvoiceInfoComponent, InvoiceInfoUpdateComponent, InvoiceInfoDeleteDialogComponent, InvoiceInfoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InvoiceprojectInvoiceInfoModule {}
