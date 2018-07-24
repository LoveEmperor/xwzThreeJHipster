import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAccessToken } from 'app/shared/model/access-token.model';

type EntityResponseType = HttpResponse<IAccessToken>;
type EntityArrayResponseType = HttpResponse<IAccessToken[]>;

@Injectable({ providedIn: 'root' })
export class AccessTokenService {
    private resourceUrl = SERVER_API_URL + 'api/access-tokens';

    constructor(private http: HttpClient) {}

    create(accessToken: IAccessToken): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(accessToken);
        return this.http
            .post<IAccessToken>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(accessToken: IAccessToken): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(accessToken);
        return this.http
            .put<IAccessToken>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAccessToken>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAccessToken[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(accessToken: IAccessToken): IAccessToken {
        const copy: IAccessToken = Object.assign({}, accessToken, {
            createTime: accessToken.createTime != null && accessToken.createTime.isValid() ? accessToken.createTime.toJSON() : null,
            updateTime: accessToken.updateTime != null && accessToken.updateTime.isValid() ? accessToken.updateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
        res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((accessToken: IAccessToken) => {
            accessToken.createTime = accessToken.createTime != null ? moment(accessToken.createTime) : null;
            accessToken.updateTime = accessToken.updateTime != null ? moment(accessToken.updateTime) : null;
        });
        return res;
    }
}
