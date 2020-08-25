import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequestBiometricData } from 'app/shared/model/request-biometric-data.model';
import { RequestBiometricDataService } from './request-biometric-data.service';

@Component({
  templateUrl: './request-biometric-data-delete-dialog.component.html',
})
export class RequestBiometricDataDeleteDialogComponent {
  requestBiometricData?: IRequestBiometricData;

  constructor(
    protected requestBiometricDataService: RequestBiometricDataService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requestBiometricDataService.delete(id).subscribe(() => {
      this.eventManager.broadcast('requestBiometricDataListModification');
      this.activeModal.close();
    });
  }
}
