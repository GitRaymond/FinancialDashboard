import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { FinancialdashboardTransactionIngModule } from './transaction-ing/transaction-ing.module';
import { FinancialdashboardVendorModule } from './vendor/vendor.module';
import { FinancialdashboardBankAccountModule } from './bank-account/bank-account.module';
import { FinancialdashboardReportingCategoryModule } from './reporting-category/reporting-category.module';
import { FinancialdashboardTagModule } from './tag/tag.module';
import { FinancialdashboardBudgetModule } from './budget/budget.module';
import { FinancialdashboardSplitTransactionModule } from './split-transaction/split-transaction.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        FinancialdashboardTransactionIngModule,
        FinancialdashboardVendorModule,
        FinancialdashboardBankAccountModule,
        FinancialdashboardReportingCategoryModule,
        FinancialdashboardTagModule,
        FinancialdashboardBudgetModule,
        FinancialdashboardSplitTransactionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FinancialdashboardEntityModule {}
