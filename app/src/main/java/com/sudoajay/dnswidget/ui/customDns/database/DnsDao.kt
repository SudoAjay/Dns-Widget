package com.sudoajay.dnswidget.ui.customDns.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DnsDao {

    @Query("Select * FROM DnsTable Where Name != :customDns  AND  Custom = :isCustomDns Or Custom = :isDefaultDns Order By Name Asc")
    fun getDnsByOption(isCustomDns: Int, isDefaultDns: Int,customDns :String): LiveData<List<Dns>>


    @Query("SELECT * FROM DnsTable Where Name LIKE :search And Name != :customDns")
    fun searchItem(search: String?,customDns :String ): LiveData<List<Dns>>

    @Query("Select Count(id) FROM DnsTable ")
    suspend fun getCount(): Int

    @Query("Select Count(id) FROM DnsTable where custom = 1 ")
    suspend fun getCustomCount(): Int

    @Query("Select * FROM DnsTable ")
    suspend fun getDns(): List<Dns>

    @Query("SELECT * FROM DnsTable Where Name != :dnsName ")
    suspend fun getList(dnsName :String): List<Dns>


    @Query("SELECT * FROM DnsTable Where id == :id ")
    suspend fun getDnsFromId(id:Long):Dns



    @Query("UPDATE DnsTable SET Name = :name  ,Dns1  = :dns1 , Dns2 = :dns2 ,Dns3 =:dns3 , Dns4 = :dns4    Where id = :id")
    suspend fun updateSelectedDns(
        id: Long,
        name: String,
        dns1: String,
        dns2: String,
        dns3: String,
        dns4: String
    )

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dns: Dns)

    @Query("DELETE FROM DnsTable Where id = :ID")
    suspend fun deleteRowFromId(ID: Long)

    @Query("DELETE FROM DnsTable")
    suspend fun deleteAll()
}