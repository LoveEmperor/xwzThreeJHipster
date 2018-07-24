import { Moment } from 'moment';

export interface IApplication {
    id?: number;
    appId?: string;
    appSecret?: string;
    createTime?: Moment;
    updateTime?: Moment;
}

export class Application implements IApplication {
    constructor(
        public id?: number,
        public appId?: string,
        public appSecret?: string,
        public createTime?: Moment,
        public updateTime?: Moment
    ) {}
}
