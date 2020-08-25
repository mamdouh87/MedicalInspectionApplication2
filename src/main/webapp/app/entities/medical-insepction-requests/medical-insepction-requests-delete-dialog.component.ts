import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicalInsepctionRequests } from 'app/shared/model/medical-insepction-requests.model';
import { MedicalInsepctionRequestsService } from './medical-insepction-requests.service';

@Component({
  templateUrl: './medical-insepction-requests-delete-dialog.component.html',
})
export class MedicalInsepctionRequestsDeleteDialogComponent {
  medicalInsepctionRequests?: IMedicalInsepctionRequests;

  constructor(
    protected medicalInsepctionRequestsService: MedicalInsepctionRequestsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medicalInsepctionRequestsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medicalInsepctionRequestsListModification');
      this.activeModal.close();
    });
  }
}
