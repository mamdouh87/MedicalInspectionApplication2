import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersons } from 'app/shared/model/persons.model';
import { PersonsService } from './persons.service';

@Component({
  templateUrl: './persons-delete-dialog.component.html',
})
export class PersonsDeleteDialogComponent {
  persons?: IPersons;

  constructor(protected personsService: PersonsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.personsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('personsListModification');
      this.activeModal.close();
    });
  }
}
