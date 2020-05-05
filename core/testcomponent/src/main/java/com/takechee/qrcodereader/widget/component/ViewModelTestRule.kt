package com.takechee.qrcodereader.widget.component

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ViewModelTestRule : TestRule {
    private val delegate = RuleChain
        .outerRule(InstantTaskExecutorRule())
        .around(CoroutinesRule())

    override fun apply(base: Statement?, description: Description?): Statement {
        return delegate.apply(base, description)
    }
}
