import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { RequestStatusComponent } from './request-status.component';
import { RequestStatusDetailComponent } from './request-status-detail.component';
import { RequestStatusUpdateComponent } from './request-status-update.component';
import { RequestStatusDeleteDialogComponent } from './request-status-delete-dialog.component';
import { requestStatusRoute } from './request-status.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(requestStatusRoute)],
  declarations: [RequestStatusComponent, RequestStatusDetailComponent, RequestStatusUpdateComponent, RequestStatusDeleteDialogComponent],
  entryComponents: [RequestStatusDeleteDialogComponent],
})
export class MedicalInspectionApplicationRequestStatusModule {}
