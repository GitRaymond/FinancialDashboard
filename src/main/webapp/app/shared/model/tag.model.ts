import { ITransactionIng } from 'app/shared/model//transaction-ing.model';

export interface ITag {
    id?: number;
    name?: string;
    transactionIngs?: ITransactionIng[];
}

export class Tag implements ITag {
    constructor(public id?: number, public name?: string, public transactionIngs?: ITransactionIng[]) {}
}
