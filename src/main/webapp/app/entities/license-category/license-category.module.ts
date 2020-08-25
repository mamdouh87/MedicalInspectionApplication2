import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { LicenseCategoryComponent } from './license-category.component';
import { LicenseCategoryDetailComponent } from './license-category-detail.component';
import { LicenseCategoryUpdateComponent } from './license-category-update.component';
import { LicenseCategoryDeleteDialogComponent } from './license-category-delete-dialog.component';
import { licenseCategoryRoute } from './license-category.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(licenseCategoryRoute)],
  declarations: [
    LicenseCategoryComponent,
    LicenseCategoryDetailComponent,
    LicenseCategoryUpdateComponent,
    LicenseCategoryDeleteDialogComponent,
  ],
  entryComponents: [LicenseCategoryDeleteDialogComponent],
})
export class MedicalInspectionApplicationLicenseCategoryModule {}
