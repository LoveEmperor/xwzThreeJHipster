import { Moment } from 'moment';

export interface IAccessToken {
    id?: number;
    nsrmc?: string;
    nsrsbh?: string;
    expires?: string;
    pkey?: string;
    createTime?: Moment;
    updateTime?: Moment;
}

export class AccessToken implements IAccessToken {
    constructor(
        public id?: number,
        public nsrmc?: string,
        public nsrsbh?: string,
        public expires?: string,
        public pkey?: string,
        public createTime?: Moment,
        public updateTime?: Moment
    ) {}
}
