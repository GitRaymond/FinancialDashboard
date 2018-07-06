import { ITransactionIng } from 'app/shared/model//transaction-ing.model';
import { IBudget } from 'app/shared/model//budget.model';

export interface IReportingCategory {
    id?: number;
    name?: string;
    transactionIngs?: ITransactionIng[];
    budgets?: IBudget[];
}

export class ReportingCategory implements IReportingCategory {
    constructor(public id?: number, public name?: string, public transactionIngs?: ITransactionIng[], public budgets?: IBudget[]) {}
}
