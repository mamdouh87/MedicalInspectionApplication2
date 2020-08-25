import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAbdominalInseption } from 'app/shared/model/abdominal-inseption.model';
import { AbdominalInseptionService } from './abdominal-inseption.service';

@Component({
  templateUrl: './abdominal-inseption-delete-dialog.component.html',
})
export class AbdominalInseptionDeleteDialogComponent {
  abdominalInseption?: IAbdominalInseption;

  constructor(
    protected abdominalInseptionService: AbdominalInseptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.abdominalInseptionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('abdominalInseptionListModification');
      this.activeModal.close();
    });
  }
}
