import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAccessToken } from 'app/shared/model/access-token.model';
import { Principal } from 'app/core';
import { AccessTokenService } from './access-token.service';

@Component({
    selector: 'jhi-access-token',
    templateUrl: './access-token.component.html'
})
export class AccessTokenComponent implements OnInit, OnDestroy {
    accessTokens: IAccessToken[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private accessTokenService: AccessTokenService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.accessTokenService.query().subscribe(
            (res: HttpResponse<IAccessToken[]>) => {
                this.accessTokens = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAccessTokens();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAccessToken) {
        return item.id;
    }

    registerChangeInAccessTokens() {
        this.eventSubscriber = this.eventManager.subscribe('accessTokenListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
