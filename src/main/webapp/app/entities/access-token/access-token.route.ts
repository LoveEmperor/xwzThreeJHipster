import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { AccessToken } from 'app/shared/model/access-token.model';
import { AccessTokenService } from './access-token.service';
import { AccessTokenComponent } from './access-token.component';
import { AccessTokenDetailComponent } from './access-token-detail.component';
import { AccessTokenUpdateComponent } from './access-token-update.component';
import { AccessTokenDeletePopupComponent } from './access-token-delete-dialog.component';
import { IAccessToken } from 'app/shared/model/access-token.model';

@Injectable({ providedIn: 'root' })
export class AccessTokenResolve implements Resolve<IAccessToken> {
    constructor(private service: AccessTokenService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((accessToken: HttpResponse<AccessToken>) => accessToken.body);
        }
        return Observable.of(new AccessToken());
    }
}

export const accessTokenRoute: Routes = [
    {
        path: 'access-token',
        component: AccessTokenComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.accessToken.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'access-token/:id/view',
        component: AccessTokenDetailComponent,
        resolve: {
            accessToken: AccessTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.accessToken.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'access-token/new',
        component: AccessTokenUpdateComponent,
        resolve: {
            accessToken: AccessTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.accessToken.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'access-token/:id/edit',
        component: AccessTokenUpdateComponent,
        resolve: {
            accessToken: AccessTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.accessToken.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const accessTokenPopupRoute: Routes = [
    {
        path: 'access-token/:id/delete',
        component: AccessTokenDeletePopupComponent,
        resolve: {
            accessToken: AccessTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'invoiceprojectApp.accessToken.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
