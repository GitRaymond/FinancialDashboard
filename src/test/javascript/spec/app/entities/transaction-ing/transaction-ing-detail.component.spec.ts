/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FinancialdashboardTestModule } from '../../../test.module';
import { TransactionIngDetailComponent } from 'app/entities/transaction-ing/transaction-ing-detail.component';
import { TransactionIng } from 'app/shared/model/transaction-ing.model';

describe('Component Tests', () => {
    describe('TransactionIng Management Detail Component', () => {
        let comp: TransactionIngDetailComponent;
        let fixture: ComponentFixture<TransactionIngDetailComponent>;
        const route = ({ data: of({ transactionIng: new TransactionIng(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [TransactionIngDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TransactionIngDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransactionIngDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.transactionIng).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
