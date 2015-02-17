package br.com.nglauber.testgroovy.activeandroid

import br.com.nglauber.testgroovy.AppDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(databaseName = AppDatabase.NAME)
public class Palavra extends BaseModel{
    @Column(columnType = Column.NORMAL)
    public String texto;
}
