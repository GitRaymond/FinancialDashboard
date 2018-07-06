/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FinancialdashboardTestModule } from '../../../test.module';
import { TransactionIngUpdateComponent } from 'app/entities/transaction-ing/transaction-ing-update.component';
import { TransactionIngService } from 'app/entities/transaction-ing/transaction-ing.service';
import { TransactionIng } from 'app/shared/model/transaction-ing.model';

describe('Component Tests', () => {
    describe('TransactionIng Management Update Component', () => {
        let comp: TransactionIngUpdateComponent;
        let fixture: ComponentFixture<TransactionIngUpdateComponent>;
        let service: TransactionIngService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [TransactionIngUpdateComponent]
            })
                .overrideTemplate(TransactionIngUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransactionIngUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransactionIngService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TransactionIng(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transactionIng = entity;
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
                    const entity = new TransactionIng();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transactionIng = entity;
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
