import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPersons, Persons } from 'app/shared/model/persons.model';
import { PersonsService } from './persons.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';

@Component({
  selector: 'jhi-persons-update',
  templateUrl: './persons-update.component.html',
})
export class PersonsUpdateComponent implements OnInit {
  isSaving = false;
  passportissuecountries: ICountry[] = [];
  birthDateDp: any;

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
    middleName: [],
    familyName: [],
    fullName: [],
    birthDate: [],
    nationalId: [],
    passportNumber: [],
    passportIssueCountryId: [],
  });

  constructor(
    protected personsService: PersonsService,
    protected countryService: CountryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ persons }) => {
      this.updateForm(persons);

      this.countryService
        .query({ filter: 'persons-is-null' })
        .pipe(
          map((res: HttpResponse<ICountry[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICountry[]) => {
          if (!persons.passportIssueCountryId) {
            this.passportissuecountries = resBody;
          } else {
            this.countryService
              .find(persons.passportIssueCountryId)
              .pipe(
                map((subRes: HttpResponse<ICountry>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICountry[]) => (this.passportissuecountries = concatRes));
          }
        });
    });
  }

  updateForm(persons: IPersons): void {
    this.editForm.patchValue({
      id: persons.id,
      firstName: persons.firstName,
      lastName: persons.lastName,
      middleName: persons.middleName,
      familyName: persons.familyName,
      fullName: persons.fullName,
      birthDate: persons.birthDate,
      nationalId: persons.nationalId,
      passportNumber: persons.passportNumber,
      passportIssueCountryId: persons.passportIssueCountryId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const persons = this.createFromForm();
    if (persons.id !== undefined) {
      this.subscribeToSaveResponse(this.personsService.update(persons));
    } else {
      this.subscribeToSaveResponse(this.personsService.create(persons));
    }
  }

  private createFromForm(): IPersons {
    return {
      ...new Persons(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      middleName: this.editForm.get(['middleName'])!.value,
      familyName: this.editForm.get(['familyName'])!.value,
      fullName: this.editForm.get(['fullName'])!.value,
      birthDate: this.editForm.get(['birthDate'])!.value,
      nationalId: this.editForm.get(['nationalId'])!.value,
      passportNumber: this.editForm.get(['passportNumber'])!.value,
      passportIssueCountryId: this.editForm.get(['passportIssueCountryId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersons>>): void {
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

  trackById(index: number, item: ICountry): any {
    return item.id;
  }
}
