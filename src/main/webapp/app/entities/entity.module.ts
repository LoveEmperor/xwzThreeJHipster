import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { InvoiceprojectApplicationModule } from './application/application.module';
import { InvoiceprojectAccessTokenModule } from './access-token/access-token.module';
import { InvoiceprojectInvoiceInfoModule } from './invoice-info/invoice-info.module';
import { InvoiceprojectEInvoiceInfoModule } from './e-invoice-info/e-invoice-info.module';
import { InvoiceprojectInvoiceReportModule } from './invoice-report/invoice-report.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        InvoiceprojectApplicationModule,
        InvoiceprojectAccessTokenModule,
        InvoiceprojectInvoiceInfoModule,
        InvoiceprojectEInvoiceInfoModule,
        InvoiceprojectInvoiceReportModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InvoiceprojectEntityModule {}
