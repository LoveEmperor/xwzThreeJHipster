import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { InvoiceInfo } from 'app/shared/model/invoice-info.model';
import { InvoiceInfoService } from './invoice-info.service';
import { InvoiceInfoComponent } from './invoice-info.component';
import { InvoiceInfoDetailComponent } from './invoice-info-detail.component';
import { InvoiceInfoUpdateComponent } from './invoice-info-update.component';
import { InvoiceInfoDeletePopupComponent } from './invoice-info-delete-dialog.component';
import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';

@Injectable({ providedIn: 'root' })
export class InvoiceInfoResolve implements Resolve<IInvoiceInfo> {
    constructor(private service: InvoiceInfoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((invoiceInfo: HttpResponse<InvoiceInfo>) => invoiceInfo.body);
        }
        return Observable.of(new InvoiceInfo());
    }
}

export const invoiceInfoRoute: Routes = [
    {
        path: 'invoice-info',
        component: InvoiceInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'invoice-info/:id/view',
        component: InvoiceInfoDetailComponent,
        resolve: {
            invoiceInfo: InvoiceInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'invoice-info/new',
        component: InvoiceInfoUpdateComponent,
        resolve: {
            invoiceInfo: InvoiceInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'invoice-info/:id/edit',
        component: InvoiceInfoUpdateComponent,
        resolve: {
            invoiceInfo: InvoiceInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const invoiceInfoPopupRoute: Routes = [
    {
        path: 'invoice-info/:id/delete',
        component: InvoiceInfoDeletePopupComponent,
        resolve: {
            invoiceInfo: InvoiceInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.invoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
