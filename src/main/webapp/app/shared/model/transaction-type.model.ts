export interface ITransactionType {
  id?: number;
  nameEn?: string;
  nameAr?: string;
  code?: string;
}

export class TransactionType implements ITransactionType {
  constructor(public id?: number, public nameEn?: string, public nameAr?: string, public code?: string) {}
}
