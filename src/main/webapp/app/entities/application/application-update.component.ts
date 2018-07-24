import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IApplication } from 'app/shared/model/application.model';
import { ApplicationService } from './application.service';

@Component({
    selector: 'jhi-application-update',
    templateUrl: './application-update.component.html'
})
export class ApplicationUpdateComponent implements OnInit {
    private _application: IApplication;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(private applicationService: ApplicationService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ application }) => {
            this.application = application;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.application.createTime = moment(this.createTime, DATE_TIME_FORMAT);
        this.application.updateTime = moment(this.updateTime, DATE_TIME_FORMAT);
        if (this.application.id !== undefined) {
            this.subscribeToSaveResponse(this.applicationService.update(this.application));
        } else {
            this.subscribeToSaveResponse(this.applicationService.create(this.application));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IApplication>>) {
        result.subscribe((res: HttpResponse<IApplication>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get application() {
        return this._application;
    }

    set application(application: IApplication) {
        this._application = application;
        this.createTime = moment(application.createTime).format(DATE_TIME_FORMAT);
        this.updateTime = moment(application.updateTime).format(DATE_TIME_FORMAT);
    }
}
