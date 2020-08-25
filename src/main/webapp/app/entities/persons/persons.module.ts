import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { PersonsComponent } from './persons.component';
import { PersonsDetailComponent } from './persons-detail.component';
import { PersonsUpdateComponent } from './persons-update.component';
import { PersonsDeleteDialogComponent } from './persons-delete-dialog.component';
import { personsRoute } from './persons.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(personsRoute)],
  declarations: [PersonsComponent, PersonsDetailComponent, PersonsUpdateComponent, PersonsDeleteDialogComponent],
  entryComponents: [PersonsDeleteDialogComponent],
})
export class MedicalInspectionApplicationPersonsModule {}
