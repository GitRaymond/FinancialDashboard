/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FinancialdashboardTestModule } from '../../../test.module';
import { SplitTransactionComponent } from 'app/entities/split-transaction/split-transaction.component';
import { SplitTransactionService } from 'app/entities/split-transaction/split-transaction.service';
import { SplitTransaction } from 'app/shared/model/split-transaction.model';

describe('Component Tests', () => {
    describe('SplitTransaction Management Component', () => {
        let comp: SplitTransactionComponent;
        let fixture: ComponentFixture<SplitTransactionComponent>;
        let service: SplitTransactionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [SplitTransactionComponent],
                providers: []
            })
                .overrideTemplate(SplitTransactionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SplitTransactionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SplitTransactionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SplitTransaction(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.splitTransactions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
