import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { OphthalmicInspectionComponent } from './ophthalmic-inspection.component';
import { OphthalmicInspectionDetailComponent } from './ophthalmic-inspection-detail.component';
import { OphthalmicInspectionUpdateComponent } from './ophthalmic-inspection-update.component';
import { OphthalmicInspectionDeleteDialogComponent } from './ophthalmic-inspection-delete-dialog.component';
import { ophthalmicInspectionRoute } from './ophthalmic-inspection.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(ophthalmicInspectionRoute)],
  declarations: [
    OphthalmicInspectionComponent,
    OphthalmicInspectionDetailComponent,
    OphthalmicInspectionUpdateComponent,
    OphthalmicInspectionDeleteDialogComponent,
  ],
  entryComponents: [OphthalmicInspectionDeleteDialogComponent],
})
export class MedicalInspectionApplicationOphthalmicInspectionModule {}
