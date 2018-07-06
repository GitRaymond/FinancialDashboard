/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FinancialdashboardTestModule } from '../../../test.module';
import { SplitTransactionDetailComponent } from 'app/entities/split-transaction/split-transaction-detail.component';
import { SplitTransaction } from 'app/shared/model/split-transaction.model';

describe('Component Tests', () => {
    describe('SplitTransaction Management Detail Component', () => {
        let comp: SplitTransactionDetailComponent;
        let fixture: ComponentFixture<SplitTransactionDetailComponent>;
        const route = ({ data: of({ splitTransaction: new SplitTransaction(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [SplitTransactionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SplitTransactionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SplitTransactionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.splitTransaction).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
