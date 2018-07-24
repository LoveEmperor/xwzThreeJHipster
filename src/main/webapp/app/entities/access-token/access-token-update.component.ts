import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAccessToken } from 'app/shared/model/access-token.model';
import { AccessTokenService } from './access-token.service';

@Component({
    selector: 'jhi-access-token-update',
    templateUrl: './access-token-update.component.html'
})
export class AccessTokenUpdateComponent implements OnInit {
    private _accessToken: IAccessToken;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(private accessTokenService: AccessTokenService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ accessToken }) => {
            this.accessToken = accessToken;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.accessToken.createTime = moment(this.createTime, DATE_TIME_FORMAT);
        this.accessToken.updateTime = moment(this.updateTime, DATE_TIME_FORMAT);
        if (this.accessToken.id !== undefined) {
            this.subscribeToSaveResponse(this.accessTokenService.update(this.accessToken));
        } else {
            this.subscribeToSaveResponse(this.accessTokenService.create(this.accessToken));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAccessToken>>) {
        result.subscribe((res: HttpResponse<IAccessToken>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get accessToken() {
        return this._accessToken;
    }

    set accessToken(accessToken: IAccessToken) {
        this._accessToken = accessToken;
        this.createTime = moment(accessToken.createTime).format(DATE_TIME_FORMAT);
        this.updateTime = moment(accessToken.updateTime).format(DATE_TIME_FORMAT);
    }
}
