import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITrafficUnits, TrafficUnits } from 'app/shared/model/traffic-units.model';
import { TrafficUnitsService } from './traffic-units.service';

@Component({
  selector: 'jhi-traffic-units-update',
  templateUrl: './traffic-units-update.component.html',
})
export class TrafficUnitsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    trafficUnitNameEn: [],
    trafficUnitNameAr: [],
    trafficUnitCode: [],
  });

  constructor(protected trafficUnitsService: TrafficUnitsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trafficUnits }) => {
      this.updateForm(trafficUnits);
    });
  }

  updateForm(trafficUnits: ITrafficUnits): void {
    this.editForm.patchValue({
      id: trafficUnits.id,
      trafficUnitNameEn: trafficUnits.trafficUnitNameEn,
      trafficUnitNameAr: trafficUnits.trafficUnitNameAr,
      trafficUnitCode: trafficUnits.trafficUnitCode,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const trafficUnits = this.createFromForm();
    if (trafficUnits.id !== undefined) {
      this.subscribeToSaveResponse(this.trafficUnitsService.update(trafficUnits));
    } else {
      this.subscribeToSaveResponse(this.trafficUnitsService.create(trafficUnits));
    }
  }

  private createFromForm(): ITrafficUnits {
    return {
      ...new TrafficUnits(),
      id: this.editForm.get(['id'])!.value,
      trafficUnitNameEn: this.editForm.get(['trafficUnitNameEn'])!.value,
      trafficUnitNameAr: this.editForm.get(['trafficUnitNameAr'])!.value,
      trafficUnitCode: this.editForm.get(['trafficUnitCode'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrafficUnits>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
