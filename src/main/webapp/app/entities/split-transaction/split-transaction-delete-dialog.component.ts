import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISplitTransaction } from 'app/shared/model/split-transaction.model';
import { SplitTransactionService } from './split-transaction.service';

@Component({
    selector: 'jhi-split-transaction-delete-dialog',
    templateUrl: './split-transaction-delete-dialog.component.html'
})
export class SplitTransactionDeleteDialogComponent {
    splitTransaction: ISplitTransaction;

    constructor(
        private splitTransactionService: SplitTransactionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.splitTransactionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'splitTransactionListModification',
                content: 'Deleted an splitTransaction'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-split-transaction-delete-popup',
    template: ''
})
export class SplitTransactionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ splitTransaction }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SplitTransactionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.splitTransaction = splitTransaction;
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
