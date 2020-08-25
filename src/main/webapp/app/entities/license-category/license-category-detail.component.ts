import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILicenseCategory } from 'app/shared/model/license-category.model';

@Component({
  selector: 'jhi-license-category-detail',
  templateUrl: './license-category-detail.component.html',
})
export class LicenseCategoryDetailComponent implements OnInit {
  licenseCategory: ILicenseCategory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ licenseCategory }) => (this.licenseCategory = licenseCategory));
  }

  previousState(): void {
    window.history.back();
  }
}
