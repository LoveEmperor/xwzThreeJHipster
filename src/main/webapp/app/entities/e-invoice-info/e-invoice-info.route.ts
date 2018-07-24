import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { EInvoiceInfo } from 'app/shared/model/e-invoice-info.model';
import { EInvoiceInfoService } from './e-invoice-info.service';
import { EInvoiceInfoComponent } from './e-invoice-info.component';
import { EInvoiceInfoDetailComponent } from './e-invoice-info-detail.component';
import { EInvoiceInfoUpdateComponent } from './e-invoice-info-update.component';
import { EInvoiceInfoDeletePopupComponent } from './e-invoice-info-delete-dialog.component';
import { IEInvoiceInfo } from 'app/shared/model/e-invoice-info.model';

@Injectable({ providedIn: 'root' })
export class EInvoiceInfoResolve implements Resolve<IEInvoiceInfo> {
    constructor(private service: EInvoiceInfoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((eInvoiceInfo: HttpResponse<EInvoiceInfo>) => eInvoiceInfo.body);
        }
        return Observable.of(new EInvoiceInfo());
    }
}

export const eInvoiceInfoRoute: Routes = [
    {
        path: 'e-invoice-info',
        component: EInvoiceInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.eInvoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'e-invoice-info/:id/view',
        component: EInvoiceInfoDetailComponent,
        resolve: {
            eInvoiceInfo: EInvoiceInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.eInvoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'e-invoice-info/new',
        component: EInvoiceInfoUpdateComponent,
        resolve: {
            eInvoiceInfo: EInvoiceInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.eInvoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'e-invoice-info/:id/edit',
        component: EInvoiceInfoUpdateComponent,
        resolve: {
            eInvoiceInfo: EInvoiceInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.eInvoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const eInvoiceInfoPopupRoute: Routes = [
    {
        path: 'e-invoice-info/:id/delete',
        component: EInvoiceInfoDeletePopupComponent,
        resolve: {
            eInvoiceInfo: EInvoiceInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.eInvoiceInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
