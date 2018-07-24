import { Moment } from 'moment';

export interface IEInvoiceInfo {
    id?: number;
    fpdm?: string;
    fphm?: string;
    kprq?: string;
    xfsh?: string;
    xfmc?: string;
    gfsh?: string;
    gfmc?: string;
    jehj?: string;
    sehj?: string;
    url?: string;
    createTime?: Moment;
    updateTime?: Moment;
}

export class EInvoiceInfo implements IEInvoiceInfo {
    constructor(
        public id?: number,
        public fpdm?: string,
        public fphm?: string,
        public kprq?: string,
        public xfsh?: string,
        public xfmc?: string,
        public gfsh?: string,
        public gfmc?: string,
        public jehj?: string,
        public sehj?: string,
        public url?: string,
        public createTime?: Moment,
        public updateTime?: Moment
    ) {}
}
