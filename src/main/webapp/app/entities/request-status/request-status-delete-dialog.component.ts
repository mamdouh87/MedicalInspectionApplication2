import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequestStatus } from 'app/shared/model/request-status.model';
import { RequestStatusService } from './request-status.service';

@Component({
  templateUrl: './request-status-delete-dialog.component.html',
})
export class RequestStatusDeleteDialogComponent {
  requestStatus?: IRequestStatus;

  constructor(
    protected requestStatusService: RequestStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requestStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('requestStatusListModification');
      this.activeModal.close();
    });
  }
}
