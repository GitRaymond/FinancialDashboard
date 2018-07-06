/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FinancialdashboardTestModule } from '../../../test.module';
import { SplitTransactionDeleteDialogComponent } from 'app/entities/split-transaction/split-transaction-delete-dialog.component';
import { SplitTransactionService } from 'app/entities/split-transaction/split-transaction.service';

describe('Component Tests', () => {
    describe('SplitTransaction Management Delete Component', () => {
        let comp: SplitTransactionDeleteDialogComponent;
        let fixture: ComponentFixture<SplitTransactionDeleteDialogComponent>;
        let service: SplitTransactionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [SplitTransactionDeleteDialogComponent]
            })
                .overrideTemplate(SplitTransactionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SplitTransactionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SplitTransactionService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
