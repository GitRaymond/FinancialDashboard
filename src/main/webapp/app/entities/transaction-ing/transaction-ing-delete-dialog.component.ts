import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransactionIng } from 'app/shared/model/transaction-ing.model';
import { TransactionIngService } from './transaction-ing.service';

@Component({
    selector: 'jhi-transaction-ing-delete-dialog',
    templateUrl: './transaction-ing-delete-dialog.component.html'
})
export class TransactionIngDeleteDialogComponent {
    transactionIng: ITransactionIng;

    constructor(
        private transactionIngService: TransactionIngService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.transactionIngService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'transactionIngListModification',
                content: 'Deleted an transactionIng'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-transaction-ing-delete-popup',
    template: ''
})
export class TransactionIngDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transactionIng }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TransactionIngDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.transactionIng = transactionIng;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
