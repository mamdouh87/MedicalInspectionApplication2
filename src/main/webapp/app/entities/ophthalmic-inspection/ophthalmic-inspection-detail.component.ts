import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOphthalmicInspection } from 'app/shared/model/ophthalmic-inspection.model';

@Component({
  selector: 'jhi-ophthalmic-inspection-detail',
  templateUrl: './ophthalmic-inspection-detail.component.html',
})
export class OphthalmicInspectionDetailComponent implements OnInit {
  ophthalmicInspection: IOphthalmicInspection | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ophthalmicInspection }) => (this.ophthalmicInspection = ophthalmicInspection));
  }

  previousState(): void {
    window.history.back();
  }
}
