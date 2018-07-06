/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FinancialdashboardTestModule } from '../../../test.module';
import { SplitTransactionUpdateComponent } from 'app/entities/split-transaction/split-transaction-update.component';
import { SplitTransactionService } from 'app/entities/split-transaction/split-transaction.service';
import { SplitTransaction } from 'app/shared/model/split-transaction.model';

describe('Component Tests', () => {
    describe('SplitTransaction Management Update Component', () => {
        let comp: SplitTransactionUpdateComponent;
        let fixture: ComponentFixture<SplitTransactionUpdateComponent>;
        let service: SplitTransactionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [SplitTransactionUpdateComponent]
            })
                .overrideTemplate(SplitTransactionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SplitTransactionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SplitTransactionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SplitTransaction(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.splitTransaction = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SplitTransaction();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.splitTransaction = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
