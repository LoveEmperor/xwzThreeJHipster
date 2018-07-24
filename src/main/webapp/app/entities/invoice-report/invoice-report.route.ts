import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { InvoiceReport } from 'app/shared/model/invoice-report.model';
import { InvoiceReportService } from './invoice-report.service';
import { InvoiceReportComponent } from './invoice-report.component';
import { InvoiceReportDetailComponent } from './invoice-report-detail.component';
import { InvoiceReportUpdateComponent } from './invoice-report-update.component';
import { InvoiceReportDeletePopupComponent } from './invoice-report-delete-dialog.component';
import { IInvoiceReport } from 'app/shared/model/invoice-report.model';

@Injectable({ providedIn: 'root' })
export class InvoiceReportResolve implements Resolve<IInvoiceReport> {
    constructor(private service: InvoiceReportService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((invoiceReport: HttpResponse<InvoiceReport>) => invoiceReport.body);
        }
        return Observable.of(new InvoiceReport());
    }
}

export const invoiceReportRoute: Routes = [
    {
        path: 'invoice-report',
        component: InvoiceReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'invoice-report/:id/view',
        component: InvoiceReportDetailComponent,
        resolve: {
            invoiceReport: InvoiceReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'invoice-report/new',
        component: InvoiceReportUpdateComponent,
        resolve: {
            invoiceReport: InvoiceReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'invoice-report/:id/edit',
        component: InvoiceReportUpdateComponent,
        resolve: {
            invoiceReport: InvoiceReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const invoiceReportPopupRoute: Routes = [
    {
        path: 'invoice-report/:id/delete',
        component: InvoiceReportDeletePopupComponent,
        resolve: {
            invoiceReport: InvoiceReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
