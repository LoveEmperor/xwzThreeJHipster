import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InvoiceprojectSharedModule } from 'app/shared';
import {
    AccessTokenComponent,
    AccessTokenDetailComponent,
    AccessTokenUpdateComponent,
    AccessTokenDeletePopupComponent,
    AccessTokenDeleteDialogComponent,
    accessTokenRoute,
    accessTokenPopupRoute
} from './';

const ENTITY_STATES = [...accessTokenRoute, ...accessTokenPopupRoute];

@NgModule({
    imports: [InvoiceprojectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AccessTokenComponent,
        AccessTokenDetailComponent,
        AccessTokenUpdateComponent,
        AccessTokenDeleteDialogComponent,
        AccessTokenDeletePopupComponent
    ],
    entryComponents: [AccessTokenComponent, AccessTokenUpdateComponent, AccessTokenDeleteDialogComponent, AccessTokenDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InvoiceprojectAccessTokenModule {}
