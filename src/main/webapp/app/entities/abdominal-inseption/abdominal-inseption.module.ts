import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { AbdominalInseptionComponent } from './abdominal-inseption.component';
import { AbdominalInseptionDetailComponent } from './abdominal-inseption-detail.component';
import { AbdominalInseptionUpdateComponent } from './abdominal-inseption-update.component';
import { AbdominalInseptionDeleteDialogComponent } from './abdominal-inseption-delete-dialog.component';
import { abdominalInseptionRoute } from './abdominal-inseption.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(abdominalInseptionRoute)],
  declarations: [
    AbdominalInseptionComponent,
    AbdominalInseptionDetailComponent,
    AbdominalInseptionUpdateComponent,
    AbdominalInseptionDeleteDialogComponent,
  ],
  entryComponents: [AbdominalInseptionDeleteDialogComponent],
})
export class MedicalInspectionApplicationAbdominalInseptionModule {}
