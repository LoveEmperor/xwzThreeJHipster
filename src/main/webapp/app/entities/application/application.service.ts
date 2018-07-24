import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApplication } from 'app/shared/model/application.model';

type EntityResponseType = HttpResponse<IApplication>;
type EntityArrayResponseType = HttpResponse<IApplication[]>;

@Injectable({ providedIn: 'root' })
export class ApplicationService {
    private resourceUrl = SERVER_API_URL + 'api/applications';

    constructor(private http: HttpClient) {}

    create(application: IApplication): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(application);
        return this.http
            .post<IApplication>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(application: IApplication): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(application);
        return this.http
            .put<IApplication>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IApplication>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IApplication[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(application: IApplication): IApplication {
        const copy: IApplication = Object.assign({}, application, {
            createTime: application.createTime != null && application.createTime.isValid() ? application.createTime.toJSON() : null,
            updateTime: application.updateTime != null && application.updateTime.isValid() ? application.updateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
        res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((application: IApplication) => {
            application.createTime = application.createTime != null ? moment(application.createTime) : null;
            application.updateTime = application.updateTime != null ? moment(application.updateTime) : null;
        });
        return res;
    }
}
