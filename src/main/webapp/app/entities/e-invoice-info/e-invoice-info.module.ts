import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InvoiceprojectSharedModule } from 'app/shared';
import {
    EInvoiceInfoComponent,
    EInvoiceInfoDetailComponent,
    EInvoiceInfoUpdateComponent,
    EInvoiceInfoDeletePopupComponent,
    EInvoiceInfoDeleteDialogComponent,
    eInvoiceInfoRoute,
    eInvoiceInfoPopupRoute
} from './';

const ENTITY_STATES = [...eInvoiceInfoRoute, ...eInvoiceInfoPopupRoute];

@NgModule({
    imports: [InvoiceprojectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EInvoiceInfoComponent,
        EInvoiceInfoDetailComponent,
        EInvoiceInfoUpdateComponent,
        EInvoiceInfoDeleteDialogComponent,
        EInvoiceInfoDeletePopupComponent
    ],
    entryComponents: [
        EInvoiceInfoComponent,
        EInvoiceInfoUpdateComponent,
        EInvoiceInfoDeleteDialogComponent,
        EInvoiceInfoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InvoiceprojectEInvoiceInfoModule {}
