/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FinancialdashboardTestModule } from '../../../test.module';
import { TransactionIngDeleteDialogComponent } from 'app/entities/transaction-ing/transaction-ing-delete-dialog.component';
import { TransactionIngService } from 'app/entities/transaction-ing/transaction-ing.service';

describe('Component Tests', () => {
    describe('TransactionIng Management Delete Component', () => {
        let comp: TransactionIngDeleteDialogComponent;
        let fixture: ComponentFixture<TransactionIngDeleteDialogComponent>;
        let service: TransactionIngService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [TransactionIngDeleteDialogComponent]
            })
                .overrideTemplate(TransactionIngDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransactionIngDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransactionIngService);
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
