import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOphthalmicInspection } from 'app/shared/model/ophthalmic-inspection.model';
import { OphthalmicInspectionService } from './ophthalmic-inspection.service';

@Component({
  templateUrl: './ophthalmic-inspection-delete-dialog.component.html',
})
export class OphthalmicInspectionDeleteDialogComponent {
  ophthalmicInspection?: IOphthalmicInspection;

  constructor(
    protected ophthalmicInspectionService: OphthalmicInspectionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ophthalmicInspectionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ophthalmicInspectionListModification');
      this.activeModal.close();
    });
  }
}
