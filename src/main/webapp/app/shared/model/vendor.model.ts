import { ITransactionIng } from 'app/shared/model//transaction-ing.model';

export interface IVendor {
    id?: number;
    name?: string;
    transactionIngs?: ITransactionIng[];
}

export class Vendor implements IVendor {
    constructor(public id?: number, public name?: string, public transactionIngs?: ITransactionIng[]) {}
}
