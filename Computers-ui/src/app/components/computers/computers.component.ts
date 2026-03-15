import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { debounceTime } from 'rxjs';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatSortModule, Sort } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { ComputerDto } from '../../models/computer.model';
import { ComputerService } from '../../services/computer.service';

@Component({
  selector: 'app-computers',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './computers.component.html',
  styleUrl: './computers.component.css'
})
export class ComputersComponent implements OnInit {
  displayedColumns: string[] = ['name', 'accountingDate', 'costUsd', 'costPln'];
  data: ComputerDto[] = [];

  totalElements = 0;
  pageSize = 5;
  pageIndex = 0;
  sortField = 'name';
  sortDirection = 'asc';
  loading = false;
  errorMessage = '';

  filterForm = new FormGroup({
    name: new FormControl<string>(''),
    accountingDate: new FormControl<string>('')
  });

  constructor(private computerService: ComputerService) {}

  ngOnInit(): void {
    this.loadData();

    this.filterForm.valueChanges
      .pipe(debounceTime(300))
      .subscribe(() => {
        this.pageIndex = 0;
        this.loadData();
      });
  }

  loadData(): void {
    this.loading = true;
    this.errorMessage = '';

    const name = this.filterForm.value.name ?? '';
    const accountingDate = this.filterForm.value.accountingDate ?? '';

    this.computerService.getComputers(
      this.pageIndex,
      this.pageSize,
      this.sortField,
      this.sortDirection,
      name,
      accountingDate
    ).subscribe({
      next: response => {
        this.data = response.content;
        this.totalElements = response.totalElements;
        this.loading = false;
      },
      error: err => {
        console.error(err);
        this.errorMessage = err.error.message ?? 'Nie udało się pobrać danych z backendu.';
        this.loading = false;
      }
    });
  }

  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadData();
  }

  onSortChange(sort: Sort): void {
    this.sortField = sort.active || 'name';
    this.sortDirection = sort.direction || 'asc';
    this.pageIndex = 0;
    this.loadData();
  }

  clearFilters(): void {
    this.filterForm.reset({
      name: '',
      accountingDate: ''
    });
    this.pageIndex = 0;
    this.loadData();
  }
}
